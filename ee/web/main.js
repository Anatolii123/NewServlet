var s11 = document.getElementById('sz11');
var s12 = document.getElementById('sz12');
var s21 = document.getElementById('sz21');
var s22 = document.getElementById('sz22');
var firstMat = document.getElementById('matrix1');
var secondMat = document.getElementById('matrix2');
firstMat.style.top = '10%';
firstMat.style.left = '35%';
secondMat.style.top = '10%';
secondMat.style.left = '75%';

function check(input) {
    input.value = input.value.replace(/[^\d,]/g, '')
};
// function paste(element, row, column) {
//     left = 100 + 16*column;
//     top = 100 + 16*row;
//     document.body.innerHTML = '<p style=": ' + left + ' " style="top: ' + top + '">' + element.value() + '</p>';
// }

function buildMatrix(size1, size2, id, matrix) {
    var sz1 = size1.value;
    var sz2 = size2.value;
    size1.style.width = size1.value.toString() != '' ? (size1.value.toString().length*14).toString() + 'px': '14px';
    size2.style.width = size2.value.toString() != '' ? (size2.value.toString().length*14).toString() + 'px': '14px';
    s21.style.width = s11.style.width;
    s22.style.width = s12.style.width;
    var mainrow = '<tr><td><input type="text" maxlength="50" size="5" name="' + id.toString() + '11"' +
        'onkeyup="return check(this);" onchange="return check(this);"></td>';
    for (var i = 1; i < sz2; i++) {
        mainrow += '<td><input type="text" maxlength="50" size="5" name="' + id.toString() + '1' + (i+1).toString() +
            '" onkeyup="return check(this);" onchange="return check(this);"></td>';
    }
    mainrow += '</tr>';
    var tableHTML = mainrow;
    for (var i = 1; i < sz1; i++){
        row = '<tr><td><input type="text" maxlength="50" size="5" name="' + id.toString() + (i+1).toString() +
            '1" onkeyup="return check(this);" onchange="return check(this);"></td>';
        for (var j = 1; j < sz2; j++) {
            row += '<td><input type="text" maxlength="50" size="5" name="' + id.toString() + (i+1).toString() + (j+1).toString() +
                '" onkeyup="return check(this);" onchange="return check(this);"></td>';
        }
        row += '</tr>';
        tableHTML += row;
    }
    matrix.innerHTML = tableHTML;

    matrix.createCaption().innerHTML = "<b>Матрица " + id.toString() + "</b>";
    if (document.body.className == "Change"){
        matrix.caption.style.color = "white";
    }
    document.body.appendChild(matrix);
}
buildMatrix(s11,s12,1,firstMat);
buildMatrix(s21,s22,2,secondMat);

cbx.onclick = function() {
    if(document.body.className != "Change") {
        document.account.classList.add("Change");
        document.account.classList.remove("Unchange");
        document.body.classList.add("Change");
        document.body.classList.remove("Unchange");
        firstMat.caption.style.color = "white";
        firstMat.style.borderColor = "rgb(51,54,57)";
        secondMat.caption.style.color = "white";
        secondMat.style.borderColor = "rgb(51,54,57)";
        document.mtx.classList.add("Change");
        document.mtx.classList.remove("Unchange");
        return;
    }
    document.body.classList.add("Unchange");
    document.body.classList.remove("Change");
    document.account.classList.add("Unchange");
    document.account.classList.remove("Change");
    document.mtx.classList.add("Unchange");
    document.mtx.classList.remove("Change");
    firstMat.caption.style.color = "black";
    firstMat.style.borderColor = "white";
    secondMat.caption.style.color = "black";
    secondMat.style.borderColor = "white";
}