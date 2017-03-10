/* Compiled with love by Gruntfile.js */
var TransactionType = [
 WALLET = {    "id": 1,   "value":  "Wallet",   "logo": "wallet_money_logo.png",   "isCreditCard": false  }

]
var valueOfTransactionType = function (id) {
    for(var t in TransactionType){
        if(TransactionType[t].id == id){
            return TransactionType[t];
        }
    }
    return null;
}