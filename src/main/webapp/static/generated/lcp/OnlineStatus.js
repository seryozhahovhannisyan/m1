/* Compiled with love by Gruntfile.js */
var OnlineStatus = [
 ONLINE = {    "key": 1,   "status":  "Online"  },
 AWAY = {    "key": 2,   "status":  "Away"  },
 DO_NOT_DISTURB = {    "key": 3,   "status":  "Do not Disturb"  },
 INVISIBLE = {    "key": 4,   "status":  "Invisible"  },
 OFFLINE = {    "key": 5,   "status":  "Offline"  }

]
var valueOfOnlineStatus = function (id) {
    for(var t in OnlineStatus){
        if(OnlineStatus[t].id == id){
            return OnlineStatus[t];
        }
    }
    return null;
}