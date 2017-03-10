/* Compiled with love by Gruntfile.js */
var TransactionRationalDuration = [
 MINUTES_5 = {    "id": 1,   "duration":  300,   "description":    "page.wallet.duration.minute.5"  },
 MINUTES_15 = {    "id": 2,   "duration":  900,   "description":    "page.wallet.duration.minute.15"  },
 HALF_HOUR = {    "id": 3,   "duration":  1800,   "description":   "page.wallet.duration.minute.hour.half"  },
 HOUR = {    "id": 4,   "duration":  3600,   "description":   "page.wallet.duration.minute.hour"  },
 DAY = {    "id": 5,   "duration":  86400,   "description":  "page.wallet.duration.minute.day"  }

]
var valueOfTransactionRationalDuration = function (id) {
    for(var t in TransactionRationalDuration){
        if(TransactionRationalDuration[t].id == id){
            return TransactionRationalDuration[t];
        }
    }
    return null;
}