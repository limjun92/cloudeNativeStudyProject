import { createWebHistory, createRouter } from "vue-router";
import Home from "../views/HomeMain.vue";
import CalendarMain from "../views/CalendarMain.vue";
import AlbumMain from "../views/AlbumMain.vue";
//import CalendarMain from "../views/CalendarMain.vue";
//import BoardMain from "../views/PostMain.vue";
//import BoardDetail from "../components/board/boardDetail.vue";
//import BoardWrite from "../components/board/boardWrite.vue";
//import BoardUpdate from "../components/board/boardUpdate.vue";
import PostMain from "../views/PostMain.vue";
// import TestBlog from "../views/TestBlog.vue"
///import PostDetailMain from "../views/PostDetailMain.vue";
//import PostInsertMain from "../views/PostInsertMain.vue";

import PostList from "../components/post/postList.vue";
import PostDetail from "../components/post/postDetail.vue";
import PostInsert from "../components/post/postInsert.vue";
import PostUpdate from "../components/post/postUpdate.vue";

import modalUI from "../components/common/modalUI.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/Album",
    name: "AlbumMain",
    component: AlbumMain
  },
  // {
  //   path: "/TestBlog",
  //   name: "TestBlog",
  //   component: TestBlog,
  // },
  {
    path: "/Calendar",
    name: "CalendarMain",
    component: CalendarMain,
    children:[
      {
        path:":content", 
        name: "content", 
        children:[
          {
            path:":param1", 
            name: "param1", 
            component: modalUI,
          },
          {
            path:":param1/:param2",
            name: "param2", 
            component: modalUI,
          }
        ]
      }
    ]
  },
  {
    path: "/Post",
    name: "Post",
    component: PostMain,
    children:[
      {
        path : "List", 
        name: "PostList", 
        component: PostList,
        children:[
          {
            path:":page",
            name:"Page",
            component: PostList,
            children:[
              {path:":level1", name: "Ctgrlevel1", component: PostList},
              {path:":level1/:level2", name: "Ctgrlevel2", component: PostList},
              {path:":level1/:level2/:level3", name: "Ctgrlevel3", component: PostList}
            ]
          }
          
        ]
      },
      {path : ":id", name: "PostDetail", component: PostDetail},
      {path : "Write", name: "PostInsert", component: PostInsert},
      {path : "Update/:id", name: "PostUpdate", component: PostUpdate},
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;