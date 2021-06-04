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
    enemyhealtcurrent = document.getElementById('enemyhealthcurrent')

    if(heromove == "normalmove"){
        damage = parseInt(attack.innerText)
        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage
        }
    }

    if(heromove == "firstmove" && (parseInt(manacurrent.innerText) - 10) >=0){
        manacurrent.innerText = parseInt( manacurrent.innerText) -10
        damage = parseInt(attack.innerText) * 1.5
        if((parseInt(enemyhealtcurrent.innerText) - damage) <= 0 ){
            enemyhealtcurrent.innerText = 0
        }
        else{
            enemyhealtcurrent.innerText = parseInt(enemyhealtcurrent.innerText) - damage
        }
    }

}

