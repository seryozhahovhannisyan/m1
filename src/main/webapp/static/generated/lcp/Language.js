/* Compiled with love by Gruntfile.js */
var Language = [
 ARMENIAN = {    "value": 1,   "key":  "hy",   "title":  "Armenian",   "locale":  new Locale("hy")  },
 ENGLISH = {    "value": 2,   "key":  "en",   "title":  "English",   "locale":  Locale.ENGLISH  },
 RUSSIAN = {    "value": 3,   "key":  "ru",   "title":  "Russian",   "locale":  new Locale("ru")  },
 PERSIAN = {    "value": 6,   "key":  "fa",   "title":  "Persian",   "locale":  new Locale("fa")  }

]
var valueOfLanguage = function (id) {
    for(var t in Language){
        if(Language[t].id == id){
            return Language[t];
        }
    }
    return null;
}