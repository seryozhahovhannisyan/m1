/* Compiled with love by Gruntfile.js */
var Status = [
 ACTIVE = {    "key": 1,   "status":  "active"  },
 //    CONVERTED = {    "key": 2,   "status":  "converted"  },
 DELETED = {    "key": 3,   "status":  "deleted"  },
 HIDDEN = {    "key": 4,   "status":  "hidden"  },
 BLOCKED = {    "key": 5,   "status":  "blocked"  },
 UNVERIFIED = {    "key": 6,   "status":  "unverified"  },
 UNCONVERTED = {    "key": 7,   "status":  "unconverted"  },
 PENDING = {    "key": 8,   "status":  "pending"  }

]
var valueOfStatus = function (id) {
    for(var t in Status){
        if(Status[t].id == id){
            return Status[t];
        }
    }
    return null;
}