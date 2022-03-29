package com.thepepeyt.geocraft.commands.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {

    private StringMatcher() {
    }

    public static List<Match> match(String base, List<String> possibilities) {
        possibilities.sort((a, b) -> Integer.compare(b.length(), a.length()));

        int baseLength = base.length();
        Match bestMatch = new Match(base, -1);
        List<Match> otherMatches = new ArrayList<>();

        for (String poss : possibilities) {
            if (!poss.isEmpty()) {
                int matches = 0, pos = -1;

                for (int i = 0; i < Math.min(baseLength, poss.length()); i++) {
                    if (base.charAt(i) == poss.charAt(i)) {
                        if (pos != -1) {
                            break;
                        }

                        pos = i;
                    }
                }

                for (int i = 0; i < Math.min(baseLength, poss.length()); i++) {
                    if (pos != -1 && base.charAt(i) == poss.charAt(Math.min(i + pos, poss.length() - 1))) {
                        matches++;
                    }
                }

                if (matches > bestMatch.length) {
                    bestMatch = new Match(poss, matches);
                }

                if (matches > 0 && matches >= bestMatch.length && !poss.equalsIgnoreCase(bestMatch.match)) {
                    otherMatches.add(new Match(poss, matches));
                }
            }
        }

        otherMatches.add(bestMatch);
        otherMatches.sort(null);
        return otherMatches;
    }

    private static Pattern COLOR_REGEX = Pattern.compile("<*&?#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})>*");

    public static String matchColorRegex(String str) {
        Matcher matcher = COLOR_REGEX.matcher(str);

        while (matcher.find()) {
            try {
                str = str.replace(matcher.group(0), String.valueOf(net.md_5.bungee.api.ChatColor.of("#" + matcher.group(1))));
            } catch (Exception ignored) {
                // Ignore exception
            }
        }

        return str;
    }

    public static class Match implements Comparable<Match> {

        protected final String match;
        protected final int length;

        protected Match(String match, int length) {
            this.match = match;
            this.length = length;
        }

        public String getMatch() {
            return match;
        }

        @Override
        public int compareTo(Match other) {
            return Integer.compare(other.length, this.length);
        }
    }
}
