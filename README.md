# projeto-lp2-a22202518-a22207312

****** DIAGRAMA UML ******

![](diagrama.png?raw=true "Diagrama UML")




****** COMENTÁRIOS *******

Para este projeto optámos por uma implementação onde criamos um array de quadrados que por sua vez podem ou não ter peças. Optámos por este modelo em vez de um array-bidimensional pois achámos que era uma abordagem mais prática e de fácil compreensão. 

As nossas classes interagem da seguinte forma: criámos uma classe Jogo que por sua vez possui um objeto da classe Tabuleiro. Como descrito anteriormente este Tabuleiro tem um ArrayList de objetos da classe Peca e um ArrayList de objetos da classe Square. Cada Peca tem um objeto Square e cada Square tem um objeto Peca. Para além disso temos dois objetos da classe Equipa dentro da classe Jogo que representam a Equipa Preta e a Equipa Branca. Cada peça criada tem por sua vez também uma referência à equipa a que pertence.





****** VIDEO *******


https://youtu.be/IPXNHwmEfWc

****** PECA ORIGINAL ******

Para a parte da criatividade, decidimos fazer uma peça inspirada na personagem Kirby. 

Algumas das peças dadas pelos professores fazem referências a filmes e séries, na mesma ideia, a nossa peça faz referência a um jogo. O Kirby, enquanto personagem é famoso por se tornar naquilo que come, como tal, no DEISI CHESS a peça Kirby torna-se na peça que captura. 

O valor inicial da peça é 0 e conforme as peças que vai capturando ela ganha o valor das mesmas. Para além disso quando a peça captura outra, os pontos que ela ganha são a dobrar. Ou seja, se a peça
Kirby capturar uma rainha em vez de ganhar 8 pontos ela ganha 16.

Inicialmente, antes de capturar uma peça o Kirby pode-se mexer em qualquer direção mas apenas uma casa. Depois de capturar algo ela fica com esse comportamento. Para ser fácil perceber que peça o Kirby está a imitar o ícone dela vai variando.




****** OUTROS ELEMENTOS ******

Para combinar com a peça do Kirby decidimos desenhar todos os ícones. As imagens estão todas disponíveis na pasta imagens.

Para além da peça original, mudámos ainda a imagem de fundo para uma que fosse igual à imagem de fundo do jogo do Kirby. Mudámos também as cores do tabuleiro passando de preto e branco para roxo e branco pois achámos que ficava mais giro de acordo com o fundo e os ícones das peças.

Mudámos também o titulo para "kirby adventures".

<img width="723" alt="Screenshot_2024-01-02_at_22 07 20" src="https://github.com/marianalivramento-a22202518/projeto-lp2-a22202518-a22207312/assets/126899582/d25d8a7d-ef01-4214-b077-a38b28e0ba22">




****** EXEMPLOS ******

Aqui em baixo estão alguns exemplos da peça Kirby a mudar de ícone.
<img width="724" alt="Screenshot 2024-01-02 at 20 28 17" src="https://github.com/marianalivramento-a22202518/projeto-lp2-a22202518-a22207312/assets/126899582/bd095029-b31a-4b6e-98bd-69105a88f7ea">

<img width="721" alt="Screenshot 2024-01-02 at 20 36 40" src="https://github.com/marianalivramento-a22202518/projeto-lp2-a22202518-a22207312/assets/126899582/7aa77868-f65c-4640-9a51-84f65152ba66">


<img width="727" alt="Screenshot 2024-01-02 at 20 37 21" src="https://github.com/marianalivramento-a22202518/projeto-lp2-a22202518-a22207312/assets/126899582/89e51337-80a9-4e83-9129-fcdafc9ba529">





