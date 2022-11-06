package com.mcdead.wallpass;

public enum Difficulty {
    EASY(0, 2, 3),
    NORMAL(1, 1, 2),
    HARD(2, 1, 1);

    private int m_type;
    private int m_holeRadius;
    private int m_holesCount;

    private Difficulty(final int type,
                       final int holeRadius,
                       final int holesCount)
    {
        m_type = type;
        m_holeRadius = holeRadius;
        m_holesCount = holesCount;
    }

    public static Difficulty getDifficultyByType(final int type) {
        for (final Difficulty difficulty : values())
            if (difficulty.m_type == type) return difficulty;

        return null;
    }

    public int getType() {
        return m_type;
    }

    public int getHoleRadius() {
        return m_holeRadius;
    }

    public int getHolesCount() {
        return m_holesCount;
    }
}
