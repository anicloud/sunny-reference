// Created by Huang Bin on 12/22/15.

'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var openURL = require('open');
var lazypipe = require('lazypipe');
var rimraf = require('rimraf');
var wiredep = require('wiredep').stream;
var runSequence=require('run-sequence');

var app = {
    src: 'src',
    dist: 'public',
    dev: 'dev'
};
var paths = {
    scripts: [
      //  app.src + '/app.js',
        app.src + '/src/js*/**/*.js'
       // app.src + '/components*/**/*.js'
    ],
    styles: [
        app.src + '/src/css*/**/*.css'
        // app.src + '/components*/**/*.less',
        // app.src + '/styles*/**/*.less'
    ],
    views: {
        main: app.src+'/index.html',
        files: [
            app.src + '/src/view*/**/*.html'
        ]
    },
    images: [
        app.src + '/images*/**/*'
    ],
    fonts: [
        app.src + '/fonts*/**/*',
        app.src + '/bower_components/bootstrap/fonts*/*',
        app.src + '/bower_components/font-awesome/fonts*/*'
    ],
    json:[
        app.src+'/json/*.json'
    ]
};

var replacePluginMapDev={
    'sunny_bootstrap.css':'bower_components/bootstrap/dist/css',
    'simple-sidebar.css':'bower_components/simple-sidebar',
    'bootstrap-datetimepicker.css':'bower_components/eonasdan-bootstrap-datetimepicker/build/css'
};
var replacePluginMapProd={
    'sunny_bootstrap.min.css':'bower_components/bootstrap/dist/css',
    'simple-sidebar.min.css':'bower_components/simple-sidebar',
    'bootstrap-datetimepicker.min.css':'bower_components/eonasdan-bootstrap-datetimepicker/build/css'
};
///////////////////////
// Development tasks //
///////////////////////

gulp.task('styles', function () {
    return gulp.src(paths.styles)
        // .pipe($.less())
        //.pipe($.autoprefixer('>1%'))
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev+"/src"));
});

gulp.task('scripts', function () {
    return gulp.src(paths.scripts)
        //.pipe($.jshint('.jshintrc'))
        //.pipe($.jshint.reporter('jshint-stylish'))
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev+"/src"));
});

gulp.task('copy', function () {
    for(var fileName in replacePluginMapDev){
        console.log(app.src+'/replace/plugin/'+fileName);
        gulp.src(app.src+'/replace/plugin/'+fileName)
            .pipe(gulp.dest(replacePluginMapDev[fileName]))
    }
    gulp.src(paths.views.main)
        .pipe(gulp.dest(app.dev));
    gulp.src(paths.views.files)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev+'/src'));
    gulp.src(paths.images)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev));
    gulp.src(paths.fonts)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev));
    gulp.src(paths.json)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dev+'/json'));
});

gulp.task('inject', function () {
    var injectStyles = gulp.src([
        app.dev + '/**/*.css'
    ], {read: false});

    var injectScripts = gulp.src([
        app.dev + '/**/*.js',
        '!' + app.dev + '/**/*test.js'
    ]).pipe($.angularFilesort()
        .on('error', $.util.log));

    var injectOptions = {
        ignorePath: app.dev
    };

    var wiredepOptions = {
        optional: 'configuration',
        goes: 'here'
    };

    return gulp.src(paths.views.main)
        .pipe($.inject(injectStyles, injectOptions))
        .pipe($.inject(injectScripts, injectOptions))
        .pipe(wiredep(wiredepOptions))
        .pipe(gulp.dest(app.dev));
});

//////////////////////
// Production tasks //
//////////////////////

gulp.task('copy:prod', function () {
    // gulp.src(paths.views.files)
    //     .pipe(gulp.dest(app.dist));
    // gulp.src(paths.images)
    //     .pipe($.cache($.imagemin({
    //         optimizationLevel: 5,
    //         progressive: true,
    //         interlaced: true
    //     })))
    //     .pipe(gulp.dest(app.dist));
    // gulp.src(paths.fonts)
    //     .pipe(gulp.dest(app.dist));
    // gulp.src(paths.langs)
    //     .pipe(gulp.dest(app.dist+'/lang'));
    // gulp.src(paths.views.files)
    //     .pipe(gulp.dest(app.dist));
    for(var fileName in replacePluginMapProd){
        console.log(app.src+'/replace/plugin/'+fileName);
        gulp.src(app.src+'/replace/plugin/'+fileName)
            .pipe(gulp.dest(replacePluginMapProd[fileName]))
    }
    gulp.src(app.src+'/replace/index.html')
        .pipe(gulp.dest(app.dist));
    gulp.src(paths.views.files)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dist+'/src'));
    gulp.src(paths.images)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dist));
    gulp.src(paths.fonts)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dist));
    gulp.src(paths.json)
        // .pipe($.rev())
        // .pipe($.revReplace())
        .pipe(gulp.dest(app.dist+'/json'));
});

// gulp.task('minimize', function () {
//     var jsFilter = $.filter('**/*.js');
//     var cssFilter = $.filter('**/*.css');
//     return gulp.src(app.dev + '/index.html')
//         .pipe($.useref({searchPath: [app.dev+'/src', app.src+'/src']}))
//         .pipe(jsFilter)
//         .pipe($.ngAnnotate())
//         .pipe($.uglify())
//         .pipe(jsFilter.restore())
//         .pipe(cssFilter)
//         .pipe($.minifyCss({cache: true}))
//         .pipe(cssFilter.restore())
//         // .pipe($.rev())
//         // .pipe($.revReplace())
//         .pipe(gulp.dest(app.dist));
// });
gulp.task('minimize',['minimizeCss','minimizeJs']);
gulp.task('minimizeCss',function () {
  //  var cssFilter=$.filter();
    return gulp.src(app.dev+'/src/css/**/*.css')
          .pipe($.order([
              "/src/css/app.css",
              "/src/css/sunny.css",
              "/src/css/timeline.css"
          ]))
          .pipe($.concat('app.min.css'))
          .pipe($.minifyCss({cache: true}))
          .pipe(gulp.dest(app.dist+'/src/css'))
});
gulp.task('minimizeJs',function () {
    //  var jsFilter=$.filter();
    return gulp.src(app.dev+'/src/js/**/*.js')
        .pipe($.order([
            "src/js/config/language-config.js",
            "src/js/config/route-config.js",
            "src/js/service/user-service.js",
            "src/js/service/strategy-service.js",
            "src/js/service/device-service.js",
            "src/js/service/websocket-service.js",
            "src/js/service/manager-service.js",
            "src/js/service/notify-service.js",
            "src/js/controller/home-page-ctrl.js",
            "src/js/controller/strategy-ctrl.js",
            "src/js/controller/strategy-edit-ctrl.js",
            "src/js/controller/feature-edit-ctrl.js",
            "src/js/controller/device-ctrl.js",
            "src/js/controller/main-ctrl.js",
            "src/js/controller/user-ctrl.js",
            "src/js/controller/slider.js",
            "src/js/controller/repeat-control.js",
            "src/js/controller/device-detail-ctrl.js",
            "src/js/dto/featureInstance-dto.js",
            "src/js/dto/strategyInstance-dto.js",
            "src/js/sunny.js"
        ]))
        .pipe($.concat('app.min.js'))
        //.pipe($.ngAnnotate())
        //.pipe($.uglify())
        .pipe(gulp.dest(app.dist+'/src/js'))
});
gulp.task('watch', function () {
    $.watch(paths.styles)
        .pipe($.plumber())
      //  .pipe($.less())
        .pipe($.autoprefixer('>1%'))
        .pipe(gulp.dest(app.dev+'/src'))
      //  .pipe(gulp.dest(app.dist)) //add
        .pipe($.connect.reload());

    $.watch(paths.scripts)
        .pipe($.plumber())
        .pipe($.jshint('.jshintrc'))
        .pipe($.jshint.reporter('jshint-stylish'))
        .pipe(gulp.dest(app.dev+'/src'))
       // .pipe(gulp.dest(app.dist)) //add
        .pipe($.connect.reload());

    $.watch(paths.views.files)
        .pipe($.plumber())
        .pipe(gulp.dest(app.dev+'/src'))
    //    .pipe(gulp.dest(app.dist)) //add
        .pipe($.connect.reload());

 //   gulp.watch(paths.views.main, ['inject']);
 //   gulp.watch('bower.json', ['inject']);

});

gulp.task('clean', function (cb) {
    rimraf(app.dev, cb);
});

gulp.task('clean:prod', function (cb) {
    rimraf(app.dist, cb);
});

gulp.task('link', $.shell.task([
    'ln -sf '+__dirname+'/bower_components ' + app.dev + '/bower_components'
]));

gulp.task('link:prod', $.shell.task([
    'ln -sf'+__dirname+'/bower_components ' + app.dist + '/bower_components'
]));


///////////
// Build //
///////////

gulp.task('serve', function (cb) {
    runSequence(
        'build',
        'start:server',
        'start:client',
        'watch',
        cb);
});

gulp.task('serve:node',function(cb){
    runSequence(
        'build',
        'watch',
        cb);
});

gulp.task('serve:prod', function (cb) {
    runSequence(
        'build:prod',
        // 'start:server:prod',
        // 'start:client',

        cb);
});

gulp.task('build', function (cb) {
    runSequence(
        'clean',
        [
            'copy',
            'styles',
            'scripts'
        ],
        //'inject',
        'link',
        cb);
});

gulp.task('build:prod', function (cb) {
    runSequence(
        'build',
        'clean:prod',
        [
            'copy:prod',
            'minimize'
        ],
        'link:prod',
        cb);
});

gulp.task('start:client', function () {
    openURL('http://localhost:8000');
});

gulp.task('start:server', function () {
    $.connect.server({
        root: [app.dev],
        livereload: true,
        // Change this to '0.0.0.0' to access the server from outside.
        port: 8000
    });
});

gulp.task('start:server:prod', function () {
    return $.connect.server({
        root: [app.dist],
        livereload: true,
        port: 8000
    });
});

gulp.task('default', ['serve:node']);