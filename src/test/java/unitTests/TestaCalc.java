package unitTests;

import devcalc.Calc;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestaCalc {
    @Test
    public void testarsomarDoisNumeros() {
        // 1 - Preparar - Configurar -Given
        int num1 = 5;
        int num2 = 7;
        int resultadoEsperado = 12;


        //2 - Executar - When
        int resultadoAtual = Calc.somarDoisNumeros(num1, num2);

        //3- Validar - Then
        System.out.println("O resultado Esperado e " + resultadoEsperado + "\nO resultado Atual foi " + resultadoAtual);
        assertEquals(resultadoEsperado, resultadoAtual);
    }


}
