package com.example.oop_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.oop_project.model.Lutemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickFight extends AppCompatActivity {
    private Spinner aSpinner, bSpinner;
    private TextView hpATv, hpBTv, logTv;
    private Button btnAttack;
    private Button btnAbility;
    private Button startBtn;
    private View actionsLayout;
    private Lutemon fighterA, fighterB;
    private Random rand = new Random();
    private boolean aTurn;
    private int initHpA, initHpB, initXpA, initXpB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quickfight);
        //back button for exiting this page in case we find the gameplay boring :-)
        Button backBtn = findViewById(R.id.back_button);
        aSpinner = findViewById(R.id.lutemon_a);
        bSpinner = findViewById(R.id.lutemon_b);
        startBtn = findViewById(R.id.start_battle_button);
        hpATv = findViewById(R.id.hp_a);
        hpBTv = findViewById(R.id.hp_b);
        btnAttack = findViewById(R.id.btn_attack);
        btnAbility = findViewById(R.id.btn_ability);
        actionsLayout = findViewById(R.id.actions_layout);
        logTv = findViewById(R.id.battle_log);
        //im using spinners for almost all selections in the program: little pop-down menus
        //for selecting lutemons to fight
        List<Lutemon> arena = LutemonManager.getInstance().getHome();
        List<String> names = new ArrayList<>();
        for (Lutemon l : arena) names.add(l.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpinner.setAdapter(adapter);
        bSpinner.setAdapter(adapter);
        //this is a sort of a safety mechanism: should we exit the page, all the lutemons
        //will be healed back to max hp and everything will be reverted
        startBtn.setOnClickListener(v -> initBattle());
        backBtn.setOnClickListener(v -> {
            if (fighterA != null) {
                fighterA.heal(); fighterB.heal();
                fighterA.gainXp(- (fighterA.getXp() - initXpA));
                fighterB.gainXp(- (fighterB.getXp() - initXpB));
            }
            finish();
        });
    }

    private void initBattle() {
        //by this time we have 2 differing lutemons selected in the spinners, we grab their identity
        int selectedPositionA = aSpinner.getSelectedItemPosition();
        int selectedPositionB = bSpinner.getSelectedItemPosition();
        //I could not figure out why occasionally the program broke because it wanted to choose from
        //lutemons in position -1, i implemented this to prevent from the program crashing altogether
        //and avoid the lutemons reseting
        if (selectedPositionA == -1 || selectedPositionB == -1) {
            Toast.makeText(this, "Please select valid Lutemons to battle!", Toast.LENGTH_SHORT).show();
            return;
        }
        //grab the lutemons from home
        fighterA = LutemonManager.getInstance().getHome().get(selectedPositionA);
        fighterB = LutemonManager.getInstance().getHome().get(selectedPositionB);
        //similar to above: the Logcat kept complaining because of null lutemons,
        //so once again a mechanism to avoid the porgram resetting for quicker testing
        if (fighterA == null || fighterB == null) {
            Toast.makeText(this, "One or both Lutemons are not valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        aTurn = true;
        //we get the attributes of our brave fighters to display them on the screen
        initHpA = fighterA.getHp();
        initHpB = fighterB.getHp();
        initXpA = fighterA.getXp();
        initXpB = fighterB.getXp();
        //basic menu controls
        aSpinner.setVisibility(View.GONE);
        bSpinner.setVisibility(View.GONE);
        startBtn.setVisibility(View.GONE);
        hpATv.setVisibility(View.VISIBLE);
        hpBTv.setVisibility(View.VISIBLE);
        actionsLayout.setVisibility(View.VISIBLE);
        updateStatus();

        btnAttack.setOnClickListener(v -> performAction(false));
        btnAbility.setOnClickListener(v -> performAction(true));
    }

    private void performAction(boolean useAbility) {
        //here comes the exciting part: the actual battle
        //we have a quick switching mechanism: whenever it's another lutemon's turn, we switch
        //the attacker and the defender status, I found this to be easier to manage
        Lutemon attacker = aTurn ? fighterA : fighterB;
        Lutemon defender = aTurn ? fighterB : fighterA;

        int damage;
        if (useAbility) {
            //based on the attack and the defender defense, we calculate the damage
            defender.defend(attacker.attack());
            logTv.append(attacker.getName() + " uses ability on " + defender.getName() + " \n");
        } else {
            //we use randomization to make the game more interesting, the attack change depending
            //on the outcome of the random variable and the xp
            //in the beginning, all lutemons deal their base atk
            int atkValue = attacker.attack() + rand.nextInt(attacker.getXp() + 1);
            damage = Math.max(0, atkValue - defender.getDefense());
            defender.defend(atkValue);
            logTv.append(attacker.getName() + " dealt " + damage + " damage to " + defender.getName() + " \n");
        }
        updateStatus();
        //defender dead? game over (note that the attacker cannot die due to the switch system)
        if (!defender.isAlive()) {
            logTv.append(defender.getName() + " has died. " + attacker.getName() + " wins! \n");
                    attacker.gainXp(10);
            //winnder is back to home, loser is removed
            LutemonManager.getInstance().moveToHomeFromArena(attacker);
            LutemonManager.getInstance().removeLutemon(defender);
            //battle is over, no more fighting! >:(
            btnAttack.setEnabled(false);
            btnAbility.setEnabled(false);
            return;
        }
        aTurn = !aTurn;
    }

    private void updateStatus() {
        //regular status updates to display the current hp to keep track of the battle
        hpATv.setText(fighterA.getName() + " HP: " + fighterA.getHp());
        hpBTv.setText(fighterB.getName() + " HP: " + fighterB.getHp());
    }
}


