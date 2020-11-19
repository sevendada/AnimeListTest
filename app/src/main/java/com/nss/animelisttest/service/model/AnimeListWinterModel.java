package com.nss.animelisttest.service.model;

import java.util.List;

public class AnimeListWinterModel {

    private String request_hash;
    private boolean request_cached;
    private int request_cache_expiry;
    private String season_name;
    private int season_year;
    private List<AnimeBean> anime;

    public String getRequest_hash() {
        return request_hash;
    }

    public void setRequest_hash(String request_hash) {
        this.request_hash = request_hash;
    }

    public boolean isRequest_cached() {
        return request_cached;
    }

    public void setRequest_cached(boolean request_cached) {
        this.request_cached = request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public void setRequest_cache_expiry(int request_cache_expiry) {
        this.request_cache_expiry = request_cache_expiry;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public int getSeason_year() {
        return season_year;
    }

    public void setSeason_year(int season_year) {
        this.season_year = season_year;
    }

    public List<AnimeBean> getAnime() {
        return anime;
    }

    public void setAnime(List<AnimeBean> anime) {
        this.anime = anime;
    }

    public static class AnimeBean {

        private int mal_id;
        private String url;
        private String title;
        private String image_url;
        private String synopsis;
        private String type;
        private String airing_start;
        private int episodes;
        private int members;
        private String source;
        private double score;
        private boolean r18;
        private boolean kids;
        private boolean continuing;
        private List<GenresBean> genres;
        private List<ProducersBean> producers;
        private List<?> licensors;

        public int getMal_id() {
            return mal_id;
        }

        public void setMal_id(int mal_id) {
            this.mal_id = mal_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAiring_start() {
            return airing_start;
        }

        public void setAiring_start(String airing_start) {
            this.airing_start = airing_start;
        }

        public int getEpisodes() {
            return episodes;
        }

        public void setEpisodes(int episodes) {
            this.episodes = episodes;
        }

        public int getMembers() {
            return members;
        }

        public void setMembers(int members) {
            this.members = members;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public boolean isR18() {
            return r18;
        }

        public void setR18(boolean r18) {
            this.r18 = r18;
        }

        public boolean isKids() {
            return kids;
        }

        public void setKids(boolean kids) {
            this.kids = kids;
        }

        public boolean isContinuing() {
            return continuing;
        }

        public void setContinuing(boolean continuing) {
            this.continuing = continuing;
        }

        public List<GenresBean> getGenres() {
            return genres;
        }

        public void setGenres(List<GenresBean> genres) {
            this.genres = genres;
        }

        public List<ProducersBean> getProducers() {
            return producers;
        }

        public void setProducers(List<ProducersBean> producers) {
            this.producers = producers;
        }

        public List<?> getLicensors() {
            return licensors;
        }

        public void setLicensors(List<?> licensors) {
            this.licensors = licensors;
        }

        public static class GenresBean {
            /**
             * mal_id : 4
             * type : anime
             * name : Comedy
             * url : https://myanimelist.net/anime/genre/4/Comedy
             */

            private int mal_id;
            private String type;
            private String name;
            private String url;

            public int getMal_id() {
                return mal_id;
            }

            public void setMal_id(int mal_id) {
                this.mal_id = mal_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ProducersBean {
            /**
             * mal_id : 10
             * type : anime
             * name : Production I.G
             * url : https://myanimelist.net/anime/producer/10/Production_IG
             */

            private int mal_id;
            private String type;
            private String name;
            private String url;

            public int getMal_id() {
                return mal_id;
            }

            public void setMal_id(int mal_id) {
                this.mal_id = mal_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
