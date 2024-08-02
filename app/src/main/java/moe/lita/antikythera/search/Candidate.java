package moe.lita.antikythera.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import lombok.AllArgsConstructor;
import moe.lita.antikythera.data.Action;
import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Location;

@AllArgsConstructor
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

    public static Map<Location, List<Action>> findCandidates(Game game) {
        game = game.clone();
        Queue<Candidate> queue = new ArrayDeque<>();
        Map<Location, List<Action>> positions = new HashMap<>();
        queue.add(new Candidate(game.location.clone()));

        while (!queue.isEmpty()) {
            Candidate candidate = queue.poll();
            if (positions.containsKey(candidate.location)) continue;

            Location location = candidate.location;
            positions.put(location, candidate.actions);
            for (Action action : INTERMEDIATE_ACTIONS) {
                game.location = location.clone();
                if (!action.callback.apply(game)) continue;

                List<Action> actions = new ArrayList<>(candidate.actions);
                actions.add(action);
                queue.add(new Candidate(actions, game.location));
            }
        }

        Map<Location, List<Action>> res = new HashMap<>();
        for (var entry : positions.entrySet()) {
            game.location = entry.getKey().clone();
            game.softDrop();
            Location key = game.location;
            List<Action> value = entry.getValue();
            value.add(Action.HARD_DROP);
            if (!res.containsKey(key) || res.get(key).size() > value.size())
                res.put(key, value);
        }

        return res;
    }

    public final List<Action> actions;
    public final Location location;

    public Candidate(Location location) {
        this(new ArrayList<>(), location);
    }
}
