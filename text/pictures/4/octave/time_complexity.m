graphics_toolkit("gnuplot")
x = 0:0.1:100
y_n2 = (x.*x).*0.1
y_n = (x .* log(x))

hold off
plot(x,y_n2,'r')
hold on
plot(x,y_n,'b')
xticks([])
yticks([])
xlabel("N")
ylabel("Time")
legend("N^2", "N log(N)")

