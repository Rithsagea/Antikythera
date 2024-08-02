package moe.lita.antikythera.data;

import com.google.common.base.Function;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Action {
    TAP_LEFT(Game::tapLeft),
    DAS_LEFT(Game::dasLeft),
    TAP_RIGHT(Game::tapRight),
    DAS_RIGHT(Game::dasRight),
    ROTATE_CW(Game::rotateCw),
    ROTATE_CCW(Game::rotateCcw),
    SOFT_DROP(Game::softDrop),
    HARD_DROP(Game::hardDrop),
    HOLD(Game::hold);

    public final Function<Game, Boolean> callback;
}
