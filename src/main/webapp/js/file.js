/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var contador = 0;

function add(){
    
    contador++;
    
    var des = $('#desc').val();
    var uni = $('#unit').val();
    var pre = $('#price').val();
    var iva = $('#iva').val();
    
    if($('#tieneIVA').is(':checked')){
        alert(true);
    } else {
        alert(false);
    }
    
//    alert(des + " " + uni + " " + pre + " " + iva + " " + tIV);
}
