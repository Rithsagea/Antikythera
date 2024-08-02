package moe.lita.antikythera.data;

import lombok.Getter;

@Getter
public enum Tetromino {
    I(KickTable.KICK_TABLE_I_PIECE, "-1,0|0,0|1,0|2,0"),
    J(KickTable.KICK_TABLE_DEFAULT, "-1,1|-1,0|0,0|1,0"),
    L(KickTable.KICK_TABLE_DEFAULT, "-1,0|0,0|1,0|1,1"),
    O(KickTable.KICK_TABLE_O_PIECE, "0,0|0,1|1,1|1,0"),
    S(KickTable.KICK_TABLE_DEFAULT, "-1,0|0,0|0,1|1,1"),
    T(KickTable.KICK_TABLE_DEFAULT, "-1,0|0,0|0,1|1,0"),
    Z(KickTable.KICK_TABLE_DEFAULT, "-1,1|0,1|0,0|1,0");

    private final int[][][] data;
    private final int[][][] kickTable;

    private Tetromino(int[][][] kickTable, String str) {
        this.kickTable = kickTable;
        this.data = new int[4][4][2];

        String[] blocks = str.split("\\|");
        for (int b = 0; b < 4; b++) {
            String[] blockData = blocks[b].split(",");
            data[0][b][0] = Integer.parseInt(blockData[0]);
            data[0][b][1] = Integer.parseInt(blockData[1]);
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                data[x + 1][y][0] = data[x][y][1];
                data[x + 1][y][1] = -data[x][y][0];
            }
        }
    }
}
