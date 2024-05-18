package chap1;

class Table {

    String id;
    int value;
    Table tail;

    Table() {

    }

    Table(String i, int v, Table t) {
        id = i;
        value = v;
        tail = t;
    }

    int lookup(String key) {
        if ( id.equals(key) ) {
            return value;
        }
        else if ( tail != null) {
            return tail.lookup(key);
        }
        else {
            throw new RuntimeException("Key " + key + " is not defined" );
        }
    }

    Table update(String id, int value) {
        return new Table(id, value, this);
    }

}

class IntAndTable {

    int i;
    Table t;

    IntAndTable(int ii, Table tt) {
        i=ii;
        t=tt;
    }

}

class Tree {

    Tree left;
    String key;
    Object value;
    Tree right;

    Tree(Tree l, String k, Object v, Tree r) {
        left = l;
        key = k;
        value = v;
        right = r;
    }

    Tree insert(String key, Object binding) {
        if ( key.compareTo(this.key) < 0 ) {
            return new Tree(left.insert(key, binding), this.key, value, right);
        }
        else if ( key.compareTo(this.key) > 0 ) {
            return new Tree(left, this.key, value, right.insert(key, binding));
        }
        else {
            return new Tree(left, key, binding, right);
        }
    }

    boolean member(String key) {
        if ( key == null ) {
            return false;
        }
        if ( key.compareTo(this.key) < 0) {
            return left != null && left.member(key);
        }
        else if ( key.compareTo(this.key) == 0 ) {
            return true;
        }
        else { // it has to be key.compareTo(this.key) > 0
            return right != null && right.member(key);
        }
    }

    Object lookup(String key, Tree t) {
        if ( key == null || t == null) {
            return null;
        }
        if ( key.compareTo(this.key) < 0) {
            return lookup(key, left);
        }
        else if ( key.compareTo(this.key) == 0 ) {
            return value;
        }
        else { // it has to be key.compareTo(this.key) > 0
            return lookup(key, right);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ( !(left instanceof EmptyTree) ) {
            sb.append("{ Left: ").append(left.toString()).append(" ");
        }
        String entry = " (" + this.key + ", " + this.value + ") ";
        sb.append(entry);
        if ( !(right instanceof EmptyTree) ) {
            sb.append("Right: ").append(right.toString()).append(" }");
        }
        return sb.toString();
    }

}

class EmptyTree extends Tree {

    EmptyTree() {
        super(null, null, null, null);
    }

    Tree insert(String key, Object binding) {
        return new Tree(new EmptyTree(), key, binding, new EmptyTree());
    }

}