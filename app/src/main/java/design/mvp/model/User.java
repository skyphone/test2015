/*
 * Copyright (C) 2016 Appflate.io
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package design.mvp.model;/**
 * Created by andrzejchm on 23/04/16.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("login") public               String  login;
    @SerializedName("id") public                  int     id;
    @SerializedName("avatar_url") public          String  avatarUrl;
    @SerializedName("gravatar_id") public         String  gravatarId;
    @SerializedName("url") public                 String  url;
    @SerializedName("html_url") public            String  htmlUrl;
    @SerializedName("followers_url") public       String  followersUrl;
    @SerializedName("following_url") public       String  followingUrl;
    @SerializedName("gists_url") public           String  gistsUrl;
    @SerializedName("starred_url") public         String  starredUrl;
    @SerializedName("subscriptions_url") public   String  subscriptionsUrl;
    @SerializedName("organizations_url") public   String  organizationsUrl;
    @SerializedName("repos_url") public           String  reposUrl;
    @SerializedName("events_url") public          String  eventsUrl;
    @SerializedName("received_events_url") public String  receivedEventsUrl;
    @SerializedName("type") public                String  type;
    @SerializedName("site_admin") public          boolean siteAdmin;
    @SerializedName("name") public                String  name;
    @SerializedName("company") public             String  company;
    @SerializedName("blog") public                String  blog;
    @SerializedName("location") public            String  location;
    @SerializedName("email") public               String  email;
    @SerializedName("hireable") public            boolean hireable;
    @SerializedName("bio") public                 String  bio;
    @SerializedName("public_repos") public        int     publicRepos;
    @SerializedName("public_gists") public        int     publicGists;
    @SerializedName("followers") public           int     followers;
    @SerializedName("following") public           int     following;
    @SerializedName("created_at") public          String  createdAt;
    @SerializedName("updated_at") public          String  updatedAt;
}