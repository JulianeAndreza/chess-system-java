package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();



        //true repete indefinidamente
        while (!chessMatch.getCheckMate()) {
            try {
            UI.clearScreen();

            // ui - userinterface
            UI.printMatch(chessMatch, captured);
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

            if(capturedPiece != null) {
                captured.add(capturedPiece);
            }

            if(chessMatch.getPromoted() != null){
                System.out.print("Enter piece for promotion (B/N/R/Q): ");
                String type = scanner.nextLine().toUpperCase();
                while(!type.equals("B") && !type.equals("N") && !type.equals("Q") && !type.equals("R")){
                    System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                    type = scanner.nextLine().toUpperCase();
                }
                chessMatch.replacePromotedPiece(type);
            }

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
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }


}
