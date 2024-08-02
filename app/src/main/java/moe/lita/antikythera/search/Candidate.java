package moe.lita.antikythera.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import moe.lita.antikythera.data.Action;
import moe.lita.antikythera.data.Game;

public class Candidate {
    private static final Action[] INTERMEDIATE_ACTIONS = {
            Action.TAP_LEFT,
            Action.DAS_LEFT,
            Action.TAP_RIGHT,
            Action.DAS_RIGHT,
            Action.ROTATE_CW,
            Action.ROTATE_CCW,
            Action.SOFT_DROP,
    };

    public static Map<Game, List<Action>> findCandidates(Game game) {
        Queue<Candidate> queue = new ArrayDeque<>();
        Map<Game, List<Action>> positions = new HashMap<>();
        queue.add(new Candidate(game.clone()));

        while (!queue.isEmpty()) {
            Candidate candidate = queue.poll();
            if (positions.containsKey(candidate.game)) continue;

            positions.put(candidate.game, candidate.actions);
            for (Action action : INTERMEDIATE_ACTIONS) {
                Candidate newCandidate = generateCandidate(candidate, action);
                if (newCandidate == null) continue;
                queue.add(newCandidate);
            }
        }

        Map<Game, List<Action>> res = new HashMap<>();
        for (var entry : positions.entrySet()) {
            Game key = entry.getKey().clone();
            key.hardDrop();
            List<Action> value = entry.getValue();
            value.add(Action.HARD_DROP);
            if (!res.containsKey(key) || res.get(key).size() > value.size())
                res.put(key, value);
        }

        return res;
    }

    public final List<Action> actions;
    public final Game game;

    private static Candidate generateCandidate(Candidate candidate, Action action) {
        Game game = candidate.game.clone();
        if (!action.callback.apply(game)) return null;

        List<Action> actions = new ArrayList<>(candidate.actions);
        actions.add(action);
        return new Candidate(actions, game);
    }

    public Candidate(List<Action> actions, Game game) {
        this.actions = actions;
        this.game = game;
    }

    public Candidate(Game game) {
        this(new ArrayList<>(), game);
    }
}
