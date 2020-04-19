package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.movement.Move;
import com.solarwindsmsp.chess.movement.MovementType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Piece {

    protected final ChessBoard chessBoard;
    protected Position position;
    protected final PieceColor pieceColor;

    public abstract List<Move> getPossibleMoves();

    public int getXCoordinate() {
        return position.getX();
    }

    public int getYCoordinate() {
        return position.getY();
    }

    public void move(MovementType movementType, int newX, int newY) {
        Optional<Move> optionalMove = getPossibleMoves().stream()
                .filter(move -> move.getEndPosition().getX() == newX &&  move.getEndPosition().getY() == newY)
                .filter(move -> move.getMovementType().equals(movementType))
                .findFirst();

        if(optionalMove.isPresent()) {
            Position newPosition = optionalMove.get().getEndPosition();
            this.position.setPiece(null);
            newPosition.setPiece(this);
            this.setPosition(newPosition);
        }
    }


}
