/* Compiled with love by Gruntfile.js */
var TransactionState = [
 PREPARE = {    "id": 1,   "state":  "prepare transaction",   "description":  "enum.TransactionState.PREPARE"  },
 PENDING = {    "id": 2,   "state":  "pending transaction",   "description":  "enum.TransactionState.PENDING"  },
 TIME_OUTED = {    "id": 3,   "state":  "time outed transaction",   "description":  "enum.TransactionState.TIME_OUTED"  },
 CANCELED = {    "id": 4,   "state":  "canceled transaction",   "description":  "enum.TransactionState.CANCELED"  },
 RELEASED = {    "id": 5,   "state":  "released transaction",   "description":  "enum.TransactionState.RELEASED"  }

]
var valueOfTransactionState = function (id) {
    for(var t in TransactionState){
        if(TransactionState[t].id == id){
            return TransactionState[t];
        }
    }
    return null;
}