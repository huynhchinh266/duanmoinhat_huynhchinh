let hienThiBan = [];
for (let i = 0; i < 5; i++){
    hienThiBan[i] = ['.', '.', '.', '.', '.'];
}

function hienThi(){
    let banCaro = document.getElementById("carogame");
    let data = "";
    for (let i = 0; i<5; i++){
        data += "<br>";
        for (let j = 0; j < 5; j++)
            data += hienThiBan[i][j] + "&nbsp;&nbsp;";
    }
    banCaro.innerHTML = data;
}

let nhap = 'X';
function changeValue(){
    let x = parseInt(prompt("Nhập hàng của số cần thay"));
    let y = parseInt(prompt("Nhập cột của số cần thay"));
    
    if ((x>=1) && (x<=5) && (y>=1) && (y<=5) && (hienThiBan[x-1][y-1]==='.')){
        nhap = (nhap === 'X') ? 'O' : 'X';
        hienThiBan[x-1][y-1] = nhap;
        hienThi();
    }
    checkWinPerson();
}

function checkWinPerson(){
    let person = "";
    let check = false;
    for (let i = 0; i < 5; i++){
        if (check)
            break;
        for (let j = 0; j < 5; j++)
            if ( (hienThiBan[i][j] === 'O') || (hienThiBan[i][j] === 'X')){
                if ((i>0) && (i<4)){
                    if ( (hienThiBan[i][j] === hienThiBan[i-1][j]) && (hienThiBan[i][j]===hienThiBan[i+1][j]) )
                        check = true;
                }
                if ((j>0) && (j<4)){
                    if ( (hienThiBan[i][j] === hienThiBan[i][j-1]) && (hienThiBan[i][j]===hienThiBan[i][j+1]) )
                        check = true;
                }
                if ((i>0) && (i<4)&& (j>0) && (j<4)){
                    if (( (hienThiBan[i][j] === hienThiBan[i-1][j+1]) && (hienThiBan[i][j]===hienThiBan[i+1][j-1]) ) ||
                    ( (hienThiBan[i][j] === hienThiBan[i-1][j-1]) && (hienThiBan[i][j]===hienThiBan[i+1][j+1]) ) ){
                        check = true;
                }
                if (check){
                    person = hienThiBan[i][j];
                    break;
                }
            }
        }                    
    }
    
    if (check){
        alert("Người thắng: " + person);
        for (let i = 0; i < 5; i++)
            for (let j = 0; j < 5; j++)
                hienThiBan[i][j] = '.';
        hienThi();
    }
}