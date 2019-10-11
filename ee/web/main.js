var s11 = document.getElementById('sz11');
var s12 = document.getElementById('sz12');
var s21 = document.getElementById('sz21');
var s22 = document.getElementById('sz22');
var firstMat = document.createElement('table');
var secondMat = document.createElement('table');
firstMat.style.top = '10%';
firstMat.style.left = '35%';
secondMat.style.top = '10%';
secondMat.style.left = '75%';

function proverka(input) {
    input.value = input.value.replace(/[^\d,]/g, '')
};

function fun() {
    sz11 = s11.value;
    sz12 = s12.value;
    var mainrow = '<tr><td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
    for (var i = 0; i < sz12-1; i++) {
        mainrow += '<td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
    }
    mainrow += '</tr>';
    var tableHTML = mainrow;
    for (var i = 0; i < sz11-1; i++){
        row = '<tr><td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
        for (var j = 0; j < sz12-1; j++) {
            row += '<td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
        }
        row += '</tr>';
        tableHTML += row;
    }
    firstMat.innerHTML = tableHTML;
    firstMat.createCaption().innerHTML = "<b>Первая матрица</b>";
    if (document.body.className == "Change"){
        firstMat.caption.style.color = "white";
        for (var i = 0; i < firstMat.rows.length; i++) {
            var table = firstMat.rows[i].cells;
            for (var j = 0; j < table.length; j++) {
                table[j].classList.add("Change");
                table[j].classList.remove("Unchange");
            }
        }
    }
    document.body.appendChild(firstMat);
}
fun();
function fun2() {
    sz21 = s21.value;
    sz22 = s22.value;
    var mainrow = '<tr><td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
    for (var i = 0; i < sz22-1; i++) {
        mainrow += '<td><input type="text" maxlength="50" size="5" ' +
        'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
    }
    mainrow += '</tr>';
    var tableHTML = mainrow;
    for (var i = 0; i < sz21-1; i++){
        row2 = '<tr><td><input type="text" maxlength="50" size="5" ' +
         'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
        for (var j = 0; j < sz22-1; j++) {
            row2 += '<td><input type="text" maxlength="50" size="5" ' +
         'onkeyup="return proverka(this);" onchange="return proverka(this);"></td>';
        }
        row2 += '</tr>';
        tableHTML += row2;
    }
    secondMat.innerHTML = tableHTML;
    var table = secondMat.createCaption().innerHTML = "<b>Вторая матрица</b>";
    if (document.body.className == "Change"){
        secondMat.caption.style.color = "white";
        for (var i = 0; i < secondMat.rows.length; i++) {
            var table = secondMat.rows[i].cells;
            for (var j = 0; j < table.length; j++) {
                table[j].classList.add("Change");
                table[j].classList.remove("Unchange");
            }
        }
    }
    document.body.appendChild(secondMat);
}
fun2();

cbx.onclick = function() {
    if(document.body.className != "Change") {
        document.account.classList.add("Change");
        document.account.classList.remove("Unchange");
        document.body.classList.add("Change");
        document.body.classList.remove("Unchange");
        firstMat.caption.style.color = "white";
        firstMat.style.borderColor = "rgb(51,54,57)";
        for (var i = 0; i < firstMat.rows.length; i++) {
            var table = firstMat.rows[i].cells;
            for (var j = 0; j < table.length; j++) {
                table[j].classList.add("Change");
                table[j].classList.remove("Unchange");
                table[j].onmouseleave = function() {
                    this.classList.add("Change");
                    this.classList.remove("Unchange");
                }
            }
        }
        secondMat.caption.style.color = "white";
        secondMat.style.borderColor = "rgb(51,54,57)";
        for (var i = 0; i < secondMat.rows.length; i++) {
            var table2 = secondMat.rows[i].cells;
            for (var j = 0; j < table2.length; j++) {
                table2[j].classList.add("Change");
                table2[j].classList.remove("Unchange");
                table2[j].onmouseleave = function() {
                    this.classList.add("Change");
                    this.classList.remove("Unchange");
                }
            }
        }
        return;
    }
    document.body.classList.add("Unchange");
    document.body.classList.remove("Change");
    document.account.classList.add("Unchange");
    document.account.classList.remove("Change");
    firstMat.caption.style.color = "black";
    firstMat.style.borderColor = "white";
    for (var i = 0; i < firstMat.rows.length; i++) {
        var table = firstMat.rows[i].cells;
        for (var j = 0; j < table.length; j++) {
            table[j].style.borderColor = "white";
            if (i > 0 && j >0) {
                table[j].classList.add("Unchange");
                table[j].classList.remove("Change");
                table[j].onmouseenter = function() {
                    this.classList.add("Change");
                    this.classList.remove("Unchange");
                }
                table[j].onmouseleave = function() {
                    this.classList.add("Unchange");
                    this.classList.remove("Change");
                }
            }
        }
    }
    secondMat.caption.style.color = "black";
    secondMat.style.borderColor = "white";
    for (var i = 0; i < secondMat.rows.length; i++) {
        var table2 = secondMat.rows[i].cells;
        for (var j = 0; j < table2.length; j++) {
            table2[j].style.borderColor = "white";
            if (i > 0 && j >0) {
                table2[j].classList.add("Unchange");
                table2[j].classList.remove("Change");
                table2[j].onmouseenter = function() {
                    this.classList.add("Change");
                    this.classList.remove("Unchange");
                }
                table2[j].onmouseleave = function() {
                    this.classList.add("Unchange");
                    this.classList.remove("Change");
                }
            }
        }
    }
}