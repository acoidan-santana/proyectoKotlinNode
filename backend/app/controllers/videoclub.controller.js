const db = require("../models");
const Peli = db.videoclub;
const Op = db.Sequelize.Op;

// Create and Save a new Peli
// req --> request (contains the body)
exports.create = (req, res) => {
  // Validate request
  if (!req.body.nombre || !req.body.anio || !req.body.director) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Peli
  const peli = {
    nombre: req.body.nombre,
    anio: req.body.anio,
    director: req.body.director
  };

  // Save Peli in the database
  Peli.create(peli)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Peli."
      });
    });
};

// Retrieve all Pelis from the database.
exports.findAll = (req, res) => {

  Peli.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving pelis."
      });
    });
};

// Find a single Peli with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  Peli.findByPk(id)
    .then(data => {
      console.log("estos son los datos")
      console.log(data);
      if (!data) {
        res.status(400).send({
          message:
            "No Peli found with that id"
        })
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message);
      console.log("hola");
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving peli with id"
      });
      return;
    });
};

// Update a Peli by the id in the request
exports.update = (req, res) => {
  if (!req.body.nombre || !req.body.anio || !req.body.director) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  let id = req.params.id;
  const peli = {
    nombre: req.body.nombre,
    anio: req.body.anio,
    director: req.body.director
  };

  Peli.update(peli, { returning: true, where: { id: id } })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Peli."
      });
    });
};

// Delete a Peli with the specified id in the request
exports.delete = (req, res) => {
  let id = req.params.id;
  Peli.destroy({
    where: { id }
  }).then(() => {
    res.status(204).end();
  });
};

// Delete all Peli from the database.
exports.deleteAll = (req, res) => {
  Peli.destroy()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving pelis."
      });
    });
};