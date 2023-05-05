package com.epam.rd.autocode.floodfill;
import java.util.Arrays; 
public interface FloodFill {
    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
                return new FloodFill() {
            @Override
            public void flood(String map, FloodLogger logger) {
                logger.log(map);
                String[] rows = map.split("\n");
                char[] row = {};
                char[][] mapNew = new char[rows.length+2][rows[0].length()+2]; // for new values of Flood fill. Size = size +2 for invaluable border
                for(int i = 1; i < mapNew.length; i++)
                    Arrays.fill(mapNew[i], LAND); // fill all cells by LAND value
                int count = 0; // for checking number of LAND cells

                for(int i = 0; i < rows.length+2; i++) {
                    if(i != 0 && i != rows.length+1)
                        row = rows[i - 1].toCharArray();
                    for (int j = 0; j < rows[0].length() + 2; j++) {
                        if (i == 0 || j == 0 || i == rows.length + 1 || j == rows[0].length() + 1)
                            mapNew[i][j] = LAND;
                        else {
                            // fill cells which are flooded on this step
                            if(row[j-1] == WATER)
                            {
                                mapNew[i][j] = WATER;
                                mapNew[i+1][j] = WATER;
                                mapNew[i][j+1] = WATER;
                                mapNew[i-1][j] = WATER;
                                mapNew[i][j-1] = WATER;
                            }
                            else
                                count++;
                        }

                    }
                }
                // stop if all cells are flooded
                if(count == 0)
                    return;
                // form new map
                String mapStr = "";
                for(int i = 1; i <= rows.length; i++) {
                    for (int j = 1; j < rows[0].length() + 1; j++)
                        mapStr = mapStr + mapNew[i][j];
                    if (i != rows.length)
                        mapStr = mapStr + "\n";
                }

                flood(mapStr, logger);
            }
        };

    }

    char LAND = '█';
    char WATER = '░';
}
