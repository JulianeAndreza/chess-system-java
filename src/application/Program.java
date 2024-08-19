package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        //true repete indefinidamente
        while (true) {
            try {
            UI.clearScreen();

            // ui - userinterface
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(scanner);

            boolean[][] possibleMoves = chessMatch.possibleMoves(source);
            UI.clearScreen();
            UI.printBoard(chessMatch.getPieces(), possibleMoves);
            System.out.println();
            System.out.print("Destination: ");
            ChessPosition target = UI.readChessPosition(scanner);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                }
            catch (ChessException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }


}
