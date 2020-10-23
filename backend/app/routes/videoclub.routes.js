module.exports = app => {
    const pelis = require("../controllers/videoclub.controller.js");
  
    var router = require("express").Router();
  
    // Create a new peli
    router.post("/", pelis.create);
  
    // Retrieve all pelis
    router.get("/", pelis.findAll);
  
    // Retrieve a single peli with id
    router.get("/:id", pelis.findOne);
  
    // Update a peli with id
    router.put("/:id", pelis.update);
  
    // Delete a peli with id
    router.delete("/:id", pelis.delete);
  
    // Delete all pelis
    router.delete("/", pelis.deleteAll);
  
    app.use('/api/videoclub', router);
  };