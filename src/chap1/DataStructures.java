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

    Tree insert(String key, Object binding, Tree t) {
        if ( t == null ) {
            return new Tree(null, key, binding, null);
        }
        else if ( key.compareTo(t.key) < 0 ) {
            return new Tree(insert(key, binding, t.left), t.key, t.value, t.right);
        }
        else if ( key.compareTo(t.key) > 0 ) {
            return new Tree(t.left, t.key, t.value, insert(key, binding, t.right));
        }
        else {
            return new Tree(t.left, key, binding, t.right);
        }
    }

    boolean member(String key) {
        if ( key == null ) {
            return false;
        }
        if ( key.compareTo(this.key) < 0) {
            return this.left != null && this.left.member(key);
        }
        else if ( key.compareTo(this.key) == 0 ) {
            return true;
        }
        else { // it has to be key.compareTo(this.key) > 0
            return this.right != null && this.right.member(key);
        }
    }

    Object lookup(String key, Tree t) {
        if ( key == null || t == null) {
            return null;
        }
        if ( key.compareTo(this.key) < 0) {
            return lookup(key, this.left);
        }
        else if ( key.compareTo(this.key) == 0 ) {
            return this.value;
        }
        else { // it has to be key.compareTo(this.key) > 0
            return lookup(key, this.right);
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        if (this.left != null) {
//            sb.append(left.toString()).append(" ");
//        }
//        String entry = "(" + this.key + ", " + this.value + ")";
//        sb.append(entry);
//        if (this.right != null) {
//            sb.append(right.toString()).append(" ");
//        }
//        return sb.toString();
//    }

}