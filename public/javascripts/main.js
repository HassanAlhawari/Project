function increase_stats(idbutton){
   statpoints = document.getElementById('statpoints');
   inputstatpoints = document.getElementById('inputstatpoints')

    if(statpoints.innerText > 0) {
        statpoints.innerText = parseInt(statpoints.innerText) - 1;
        inputstatpoints.value = parseInt(inputstatpoints.value) -1;

        if(idbutton == "healthbutton"){
            healthcurrent= document.getElementById('healthcurrent');
            healthmax = document.getElementById('healthmax');
            inputhealthcurrent = document.getElementById('inputhealthcurrent');
            inputhealthmax = document.getElementById('inputhealthmax');
            healthcurrent.innerText = parseInt(healthcurrent.innerText) + 5;
            healthmax.innerText = parseInt(healthmax.innerText) + 5;
            inputhealthcurrent.value = parseInt(inputhealthcurrent.value) + 5;
            inputhealthmax.value = parseInt(inputhealthmax.value) + 5;
        }

        if(idbutton == "manabutton"){
            manacurrent = document.getElementById('manacurrent');
            manamax = document.getElementById('manamax');
            inputmanacurrent =document.getElementById('inputmanacurrent');
            inputmanamax =document.getElementById('inputmanamax');
            manacurrent.innerText = parseInt(manacurrent.innerText) + 2;
            manamax.innerText = parseInt(manamax.innerText) + 2;
            inputmanacurrent.value = parseInt(inputmanacurrent.value) + 2;
            inputmanamax.value = parseInt(inputmanamax.value) + 2;
        }

        if(idbutton == "defensebutton"){
            defense = document.getElementById('defense');
            inputdefense = document.getElementById('inputdefense');
            defense.innerText = parseInt(defense.innerText) + 1;
            inputdefense.value = parseInt(inputdefense.value) + 1;
        }

        if(idbutton == "attackbutton"){
            attack = document.getElementById('attack');
            inputattack = document.getElementById('inputattack');
            attack.innerText = parseInt(attack.innerText) + 1;
            inputattack.value = parseInt(inputattack.value) + 1;
        }

    }
}



function heroattack(heromove) {
    attack = document.getElementById('attack');
    manacurrent = document.getElementById('manacurrent');
    enemyhealtcurrent = document.getElementById('enemyhealthcurrent');

    if(heromove == "normalmove"){
        damage = parseInt(attack.innerText);
        document.getElementById('abilityheromove').innerText = "normal attack!"
        document.getElementById('descriptionheromove').innerText = "The Hero uses a normal attack - dealt "+damage+" Damage";

        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0;
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage;
            enemyattack();
        }
    }

    if(heromove == "firstmove" && (parseInt(manacurrent.innerText) - 10) >=0){
        manacurrent.innerText = parseInt( manacurrent.innerText) -10;
        damage = parseInt(attack.innerText) * 1.5;
        document.getElementById('abilityheromove').innerText = document.getElementById('firstmoveability').innerText;
        document.getElementById('descriptionheromove').innerText =document.getElementById('firstmovedescription').innerText + "- dealt "+damage+" Damage";

        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0;
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage;
            enemyattack();
        }
    }

    if(heromove == "secondmove" && (parseInt(manacurrent.innerText) - 25) >=0){
        manacurrent.innerText = parseInt( manacurrent.innerText) -25;
        damage = parseInt(attack.innerText) * 3.5;
        document.getElementById('abilityheromove').innerText = document.getElementById('secondmoveability').innerText;
        document.getElementById('descriptionheromove').innerText =document.getElementById('secondmovedescription').innerText + "- dealt "+damage+" Damage";


        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0;
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage;
            enemyattack();
        }
    }

    if(heromove == "thirdmove" && (parseInt(manacurrent.innerText) - 50) >=0){
        manacurrent.innerText = parseInt( manacurrent.innerText) -50;
        damage = parseInt(attack.innerText) * 8;
        document.getElementById('abilityheromove').innerText = document.getElementById('thirdmoveability').innerText;
        document.getElementById('descriptionheromove').innerText =document.getElementById('thirdmovedescription').innerText + "- dealt "+damage+" Damage";

        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0;
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage;
            enemyattack();
        }
    }

    if(parseInt(enemyhealtcurrent.innerText) == 0){
        inputstatpoints = document.getElementById('inputstatpoints');
        console.log("You defeated the enemy!");
        document.getElementById('actionevent').innerText = "You defeated the enemy!";
        ability = document.getElementsByClassName('highlightability');
        for(var i = 0; i < ability.length; i++) {
            ability[i].style.pointerEvents = 'none'
        }
        inputstatpoints.value = parseInt(inputstatpoints.value) + 1;
        document.getElementById('exit').style.visibility = 'visible';
        document.getElementById('continue').style.visibility = 'visible';
    }

}

function enemyattack(){
    healthcurrent= document.getElementById('healthcurrent');
    enemydamage = document.getElementById('enemyattackdamage');
    defense = document.getElementById('defense')

    damage = parseInt(enemydamage.innerText) - parseInt(defense.innerText)
    crit = Math.floor(Math.random() * 10)
    if(damage <= 0){
        damage = 1;
    }

    if(crit <= 3){
        damage = damage * 2;
        document.getElementById('descriptionenemymove').innerText = "The enemy got a crit he has dealt " + damage + " Damage";
    }else{
        document.getElementById('descriptionenemymove').innerText = "The enemy has dealt " + damage + " Damage";
    }

    if(parseInt(healthcurrent.innerText)-damage<= 0 ){
        healthcurrent.innerText = 0;
    }
    else{
        healthcurrent.innerText = parseInt(healthcurrent.innerText)-damage;
    }

    if(parseInt(healthcurrent.innerText) == 0){
        console.log("You are defeated!");
        document.getElementById('actionevent').innerText = "You are defeated!";
        ability = document.getElementsByClassName('highlightability');
        for(var i = 0; i < ability.length; i++) {
            ability[i].style.pointerEvents = 'none'
        }
        document.getElementById('exit').style.visibility = 'visible';
    }
}

function generateEnemy(){
    refreshmenue();
    randomenemy = Math.floor(Math.random() * 4)

    if(randomenemy == 1){
        document.getElementById("enemy").setAttribute("src", "/assets/images/dragon.png" );
        document.getElementById('enemyattackdamage').innerText = 20;
        document.getElementById('enemyhealthcurrent').innerText = 200;
        document.getElementById('enemyhealthmax').innerText = 200;
    }
    else if (randomenemy == 2){
        document.getElementById("enemy").setAttribute("src", "/assets/images/goblin.png" );
        document.getElementById('enemyattackdamage').innerText = 5;
        document.getElementById('enemyhealthcurrent').innerText = 70;
        document.getElementById('enemyhealthmax').innerText = 70;
    }
    else if (randomenemy ==3){
        document.getElementById("enemy").setAttribute("src", "/assets/images/ghost.png" );
        document.getElementById('enemyattackdamage').innerText = 10;
        document.getElementById('enemyhealthcurrent').innerText = 50;
        document.getElementById('enemyhealthmax').innerText = 50;
    }
    else{
        document.getElementById("enemy").setAttribute("src", "/assets/images/demon.gif" );
        document.getElementById('enemyattackdamage').innerText = 100;
        document.getElementById('enemyhealthcurrent').innerText = 500;
        document.getElementById('enemyhealthmax').innerText = 500;
    }
}

function refreshmenue(){
    document.getElementById('exit').style.visibility = 'hidden';
    document.getElementById('continue').style.visibility = 'hidden';

    ability = document.getElementsByClassName('highlightability');
    for(var i = 0; i < ability.length; i++) {
        ability[i].style.pointerEvents = 'auto'
    }
    document.getElementById('actionevent').innerText = "An enemy approached!";
    document.getElementById('descriptionenemymove').innerText = "";
    document.getElementById('abilityheromove').innerText = "";
    document.getElementById('descriptionheromove').innerText = "";
}


