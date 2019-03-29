$(document).ready(function () {
    $(".Numeros").inputmask('Regex',
        {
            regex: "^[0-9]{n}"
        });
})


function formatmoney(n) {
    n=n.toFixed(2)
    return "$ "+n.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
}