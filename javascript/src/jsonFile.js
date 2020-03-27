const fs = require('fs')


const dir = "answer.json"

module.exports = {

    save(json){
        jsonString = JSON.stringify(json)
         fs.writeFile(dir, jsonString, 'utf8', function (err) {
            if (err) {
                console.log("An error occured while writing JSON Object to File.");
                return console.log(err);
            }
        
            console.log(`JSON file has been saved: ${dir}`);
            return json
        })
        return json
    },

    update(json){
        const jsonString = JSON.stringify(json)
        fs.writeFile(dir, jsonString, 'utf8', function (err) {
            if (err) {
                console.log("An error occured while writing JSON Object to File.");
                return console.log(err);
            }
        
            console.log(`JSON file has been updated: ${dir}`);
        })
    },

    getJson(){
        return JSON.parse(fs.readFileSync(dir, 'utf8'));
    }
}
