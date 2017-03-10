/* Compiled with love by Gruntfile.js */
var LogLevel = [
 OFF = {    "value": 1,   "key":  "off"  },
 FATAL = {    "value": 2,   "key":  "fatal"  },
 ERROR = {    "value": 3,   "key":  "error"  },
 WARN = {    "value": 4,   "key":  "warn"  },
 INFO = {    "value": 5,   "key":  "info"  },
 DEBUGING = {    "value": 6,   "key":  "debuging"  },
 TRACE = {    "value": 7,   "key":  "trace"  },
 ALL = {    "value": 8,   "key":  "all"  }

]
var valueOfLogLevel = function (id) {
    for(var t in LogLevel){
        if(LogLevel[t].id == id){
            return LogLevel[t];
        }
    }
    return null;
}