class Coordinates {
    
    private final int X;

    private final int Y;

    Coordinates(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.X;
        hash = 37 * hash + this.Y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return true;
    }
    
}
