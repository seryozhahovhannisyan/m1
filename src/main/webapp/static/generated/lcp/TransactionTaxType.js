/* Compiled with love by Gruntfile.js */
var TransactionTaxType = [
 MIN = {    "id": 1,   "type":  "min"  },
 MAX = {    "id": 2,   "type":  "max"  },
 PERCENT = {    "id": 3,   "type":  "percent"  }

]
var valueOfTransactionTaxType = function (id) {
    for(var t in TransactionTaxType){
        if(TransactionTaxType[t].id == id){
            return TransactionTaxType[t];
        }
    }
    return null;
}