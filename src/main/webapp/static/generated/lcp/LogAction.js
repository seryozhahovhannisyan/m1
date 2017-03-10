/* Compiled with love by Gruntfile.js */
var LogAction = [
 CREATE = {    "value": 1,   "key":  "create"  },
 READ = {    "value": 2,   "key":  "read"  },
 UPDATE = {    "value": 3,   "key":  "update"  },
 DELETE = {    "value": 4,   "key":  "delete"  },
 INSERT = {    "value": 5,   "key":  "insert"  },
 UTIL = {    "value": 6,   "key":  "util"  },
 ATTACH = {    "value": 7,   "key":  "attach"  }

]
var valueOfLogAction = function (id) {
    for(var t in LogAction){
        if(LogAction[t].id == id){
            return LogAction[t];
        }
    }
    return null;
}