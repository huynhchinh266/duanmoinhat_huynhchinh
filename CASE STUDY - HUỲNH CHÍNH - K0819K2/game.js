var canvas = document.getElementById('game');
var context = canvas.getContext('2d');

var ball = {
    x: 200,
    y: 200,
    dx: 5,
    dy: 4,
    radius: 8,
};

var paddle = {
    width: 90,
    height: 20,
    x: 0,
    y: canvas.height - 20,
    speed: 10,
    isMovingLeft: false,
    isMovingRight: false,
};

var brickConfig = {
    offsetX: 25,
    offsetY: 25,
    margin: 25,
    width: 70,
    height: 25,
    totalRow: 3,
    totalCol: 5,
};

var isGameOver = false;
var isGameWin = false;
var userScore = 0;
var maxScore = brickConfig.totalCol * brickConfig.totalRow;

var brickList = [];
for (var i = 0; i < brickConfig.totalRow; i++) {
    for (var j = 0; j < brickConfig.totalCol; j++) {
        brickList.push({
            x: brickConfig.offsetX + j * (brickConfig.width + brickConfig.margin),
            y: brickConfig.offsetY + i * (brickConfig.height + brickConfig.margin),
            isBroken: false

        })
    }
}

document.addEventListener("keyup", function (event) {
    console.log("KEY UP");
    console.log(event);
    if (event.keyCode == 37) {
        paddle.isMovingLeft = false;
    } else if (event.keyCode == 39) {
        paddle.isMovingRight = false;
    }
});

document.addEventListener("keydown", function (event) {
    console.log("KEY DOWN");
    console.log(event);
    if (event.keyCode == 37) {
        paddle.isMovingLeft = true;
    } else if (event.keyCode == 39) {
        paddle.isMovingRight = true;
    }
});

function drawBall() {
    context.beginPath();
    context.strokeStyle = "red";
    context.arc(ball.x, ball.y, ball.radius, 0, Math.PI * 2);
    context.fillStyle = "red";
    context.fill();
    context.closePath();
}

function drawPaddle() {
    context.beginPath();
    context.rect(paddle.x, paddle.y, paddle.width, paddle.height);
    context.fillStyle = "blue";
    context.fill();
    context.closePath();
}

function drawBrick() {
    brickList.forEach(function (b) {
        if (!b.isBroken) {
            context.beginPath();
            context.rect(b.x, b.y, brickConfig.width, brickConfig.height);
            context.fillStyle = "green";
            context.fill();
            context.closePath();
        }
    });
}

function fillText() {
    context.font = "20px Arial";
    context.fillStyle = "red";
    context.fillText("Score:"+userScore, 20, 20);
}

function handleBallCollideBounds() {
    if (ball.x < ball.radius || ball.x > canvas.width - ball.radius) {
        ball.dx = -ball.dx;
    }
    if (ball.y < ball.radius) {
        ball.dy = -ball.dy;
    }
}

function handleBallCollidePaddle() {
    if (ball.x + ball.radius >= paddle.x && ball.x + ball.radius <= paddle.x + paddle.width &&
        ball.y + ball.radius >= canvas.height - paddle.height) {
        ball.dy = -ball.dy;
    }
}
function handleBallCollideBricks() {
    brickList.forEach(function (b) {
        if (!b.isBroken) {
            if (ball.x >= b.x && ball.x <= b.x + brickConfig.width &&
                ball.y + ball.radius >= b.y && ball.y - ball.radius <= b.y + brickConfig.height) {
                ball.dy = -ball.dy;
                b.isBroken = true;
                userScore += 1;
                if (userScore >= maxScore) {
                    isGameOver = true;
                    isGameWin = true;
                }
            }
        }
    });
}

function updateBallPosition() {
    ball.x += ball.dx;
    ball.y += ball.dy;
}

function updatePaddlePosition() {
    if (paddle.isMovingLeft) {
        paddle.x -= paddle.speed;
    } else if (paddle.isMovingRight) {
        paddle.x += paddle.speed;
    }

    if (paddle.x < 0) {
        paddle.x = 0;
    } else if (paddle.x > canvas.width - paddle.width) {
        paddle.x = canvas.width - paddle.width;
    }
}

function checkGameOver() {
    if (ball.y > canvas.height - ball.radius) {
        isGameOver = true;
    }
}
function handdleGameOver() {
    if (isGameWin) {
        alert("YOU WIN");
    } else {
        alert("YOU CLOSE");
    }
}

function draw() {
    if (!isGameOver) {
        context.clearRect(0, 0, canvas.width, canvas.height);
        drawBall();
        drawPaddle();
        drawBrick();
        fillText();
        handleBallCollideBounds();
        handleBallCollidePaddle();
        handleBallCollideBricks();

        updateBallPosition();
        updatePaddlePosition();
        checkGameOver();

        requestAnimationFrame(draw);
    } else {
        handdleGameOver();
    }
}
draw();
