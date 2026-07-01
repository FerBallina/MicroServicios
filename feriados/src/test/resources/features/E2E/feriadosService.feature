Feature: Inicio de sesión de usuarios

  Scenario: traer todas las fechas de feriado
    Given llamar al endpoint: /feriados/all
    Then obtener un listado con varias fechas

  Scenario Outline: Failed
    Given llamar al endpoint: "<endpoint>"
    Then obtener un listado con varias fechas

   Examples:

      | endpoint      |
      | /feriados/all |
      