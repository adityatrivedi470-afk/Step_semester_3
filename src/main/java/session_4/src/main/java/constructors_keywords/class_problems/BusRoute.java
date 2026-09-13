package constructors_keywords.class_problems;

public class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    public int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }
        int codeComparison = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeComparison != 0) {
            return codeComparison;
        }
        return this.routeName.compareTo(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] sorted = new BusRoute[routes.length];
        for (int i = 0; i < routes.length; i++) {
            sorted[i] = routes[i];
        }

        for (int i = 1; i < sorted.length; i++) {
            BusRoute key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < ranked.length; i++) {
            sb.append("\"").append(ranked[i].getRouteCode()).append("\"");
            if (i < ranked.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
