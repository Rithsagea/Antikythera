package moe.lita.antikythera.data;

public class KickTable {
    public static final int[][][] KICK_TABLE_DEFAULT = {
            // rotation 0
            {
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 }
            },

            // rotation r (1)
            {
                    { 0, 0 },
                    { 1, 0 },
                    { 1, -1 },
                    { 0, 2 },
                    { 1, 2 }
            },

            // rotation 180 (2)
            {
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 0 }
            },

            // rotation l (3)
            {
                    { 0, 0 },
                    { -1, 0 },
                    { -1, -1 },
                    { 0, 2 },
                    { -1, 2 }
            }
    };

    public static final int[][][] KICK_TABLE_I_PIECE = {
            // rotation 0
            {
                    { 0, 0 },
                    { -1, 0 },
                    { 2, 0 },
                    { -1, 0 },
                    { 2, 0 }
            },

            // rotation r (1)
            {
                    { -1, 0 },
                    { 0, 0 },
                    { 0, 0 },
                    { 0, 1 },
                    { 0, -2 }
            },

            // rotation 180 (2)
            {
                    { -1, 1 },
                    { 1, 1 },
                    { -2, 1 },
                    { 1, 0 },
                    { -2, 0 }
            },

            // rotation l (3)
            {
                    { 0, 1 },
                    { 0, 1 },
                    { 0, 1 },
                    { 0, -1 },
                    { 0, 2 }
            }
    };

    public static final int[][][] KICK_TABLE_O_PIECE = {
            { { 0, 0 } },
            { { 0, -1 } },
            { { -1, -1 } },
            { { -1, 0 } }
    };
}
