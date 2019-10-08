var rng = document.getElementById('r1');
rng.style.transform = 'rotate(90deg)';
rng.style.transformOrigin = '20px 20px';
var firstMat = document.createElement('table');
var secondMat = document.createElement('table');
firstMat.style.top = '10%';
firstMat.style.left = '35%';
secondMat.style.top = '10%';
secondMat.style.left = '35%';
function fun() {
    n = rng.value;
    var mainrow = '<tr><td> </td>';
    for (var i = 0; i < n; i++) {
        mainrow += '<td>' + "" + '</td>';
    }
    mainrow += '</tr>';
    var tableHTML = mainrow;
    for (var i = 0; i < n; i++){
        row = '<tr><td>' + "" + '</td>';
        for (var j = 0; j < n; j++) {
            row += '<td>' + "" + '</td>';
        }
        row += '</tr>';
        tableHTML += row;
    }
    firstMat.innerHTML = tableHTML;
    var table = firstMat.createCaption().innerHTML = "<b>Первая матрица</b>";
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
cbx.onclick = function() {
    if(document.body.className != "Change") {
        document.account.classList.add("Change");
        document.account.classList.remove("Unchange");
        document.body.classList.add("Change");
        document.body.classList.remove("Unchange");
        firstMat.caption.style.color = "white";
        document.myform.classList.add("Change");
        document.myform.classList.remove("Unchange");
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
        return;
    }
    document.body.classList.add("Unchange");
    document.body.classList.remove("Change");
    firstMat.caption.style.color = "black";
    document.account.classList.add("Unchange");
    document.account.classList.remove("Change");
    document.myform.classList.add("Unchange");
    document.myform.classList.remove("Change");
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
}