public class DirectorsFilter implements Filter {

    private String[] myDirectors;

    public DirectorsFilter(String dirs) {
        myDirectors = dirs.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for (String k : myDirectors) {
            if (MovieDatabase.getDirector(id).contains(k)) {
                return true;
            }
        }
        return false;
    }
}

