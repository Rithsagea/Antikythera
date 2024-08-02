package moe.lita.antikythera.search;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import moe.lita.antikythera.data.Game;

@RequiredArgsConstructor
public class Node {
    public final Game game;
    public final List<Node> edges = new ArrayList<>();

    public double score;
}
