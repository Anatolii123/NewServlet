var rng = document.getElementById('r1');
rng.style.transform = 'rotate(90deg)';
rng.style.transformOrigin = '20px 20px';
var tableObj = document.createElement('table');
tableObj.style.top = '10%';
tableObj.style.left = '35%';
function fun() {
    n = rng.value;
    tableObj.style.left = -(n).toString()+43+"%";
    var mainrow = '<tr><th> </th>';
    for (var i = 0; i < n; i++) {
        mainrow += '<th>' + i.toString(n) + '</th>';
    }
    mainrow += '</tr>';
    var tableHTML = mainrow;
    for (var i = 0; i < n; i++){
        row = '<tr><th>' + i.toString(n) + '</th>';
        for (var j = 0; j < n; j++) {
            row += '<td>' + (i*j).toString(n) + '</td>';
        }
        row += '</tr>';
        tableHTML += row;
    }
    tableObj.innerHTML = tableHTML;
    if (n != 2 && n != 3 && n != 10) {
        var table = tableObj.createCaption().innerHTML = "<b>Таблица умножения " + n + "-ричной системы счисления</b>";
    } else {
        var table = tableObj.createCaption().innerHTML = "<b>Таблица умножения " + n + "-ичной системы счисления</b>";
    }
    if (document.body.className == "Change"){
        tableObj.caption.style.color = "white";
        for (var i = 0; i < tableObj.rows.length; i++) {
            var table = tableObj.rows[i].cells;
            for (var j = 0; j < table.length; j++) {
                table[j].classList.add("Change");
                table[j].classList.remove("Unchange");
            }
        }
    }
    document.body.appendChild(tableObj);
}
fun();
cbx.onclick = function() {
    if(document.body.className != "Change") {
        document.account.classList.add("Change");
        document.account.classList.remove("Unchange");
        document.body.classList.add("Change");
        document.body.classList.remove("Unchange");
        tableObj.caption.style.color = "white";
        document.myform.classList.add("Change");
        document.myform.classList.remove("Unchange");
        tableObj.style.borderColor = "rgb(51,54,57)";
        for (var i = 0; i < tableObj.rows.length; i++) {
            var table = tableObj.rows[i].cells;
            for (var j = 0; j < table.length; j++) {
                table[j].classList.add("Change");
                table[j].classList.remove("Unchange");
                table[j].onmouseleave = function() {
                    this.classList.add("Change");
                    this.classList.remove("Unchange");
                }
            }
        }
        return;
    }
    document.body.classList.add("Unchange");
    document.body.classList.remove("Change");
    tableObj.caption.style.color = "black";
    document.account.classList.add("Unchange");
    document.account.classList.remove("Change");
    document.myform.classList.add("Unchange");
    document.myform.classList.remove("Change");
    tableObj.style.borderColor = "white";
    for (var i = 0; i < tableObj.rows.length; i++) {
        var table = tableObj.rows[i].cells;
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
}