/* Compiled with love by Gruntfile.js */
var Privilege = [
 ALL = {    "id": 1,   "name":  "Super admin",   "privilege":  "All",   "comment":  "Chairman of the Supervisory Council"  },
 CRUD_COMPANY = {    "id": 2,   "name":  "Company admin",   "privilege":  "crud company",   "comment":  "Allow to create branches and cashiers allowed manage company and branch's data"  },
 CRUD_BRANCH = {    "id": 3,   "name":  "Branch admin",   "privilege":   "crud branch",   "comment":   "Allow to create cashiers allowed manage branch's data"  },
 CRUD_CASHIER = {    "id": 4,   "name":  "Cashier admin",   "privilege":  "crud cashier",   "comment":  "Allow to create cashiers allowed manage others cashier's data"  },
 CASHIER = {    "id": 5,   "name":  "Simple cashier",   "privilege": "cashier",   "comment":  "Allow to make transactions"  }

]
var valueOfPrivilege = function (id) {
    for(var t in Privilege){
        if(Privilege[t].id == id){
            return Privilege[t];
        }
    }
    return null;
}