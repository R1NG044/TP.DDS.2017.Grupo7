$(function () {
        $("#btn-agregar-condicion")
            .click(
                function () {
                    $('#tabla-metodologias').append(
                        $('<tr><td>'
                            
                            + $('#indicadorCondicion').find("option:selected").text()
                            + '</td><td>'
							+ $('#criterioCondicion').find("option:selected").text()
                            + '</td><td>'
                            + $('#valor').val()
                            + '</td>'));
                }
            );
    }
);