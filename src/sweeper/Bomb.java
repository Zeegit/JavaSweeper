package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }

    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().getX() * Ranges.getSize().getY() / 2;
        if (totalBomb > maxBombs) {
            totalBomb = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (bombMap.get(coord) == Box.BOMB) {
                continue;
            }
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (bombMap.get(around) != Box.BOMB) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }

    }
}
