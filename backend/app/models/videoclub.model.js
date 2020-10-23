module.exports = (sequelize, Sequelize) => {
    const Peli = sequelize.define("peli", {
      nombre: {
        type: Sequelize.STRING
      },
      anio: {
        type: Sequelize.INTEGER
      },
      director: {
        type: Sequelize.STRING
      }
    }, { timestamps: false});
  
    return Peli;
  };