Eclipse IDE Setup

1. Shortcuts: Window > Preferences > General > Keys
	Run = F1
	Debug = CTRL+F1

2. Font Size : Window > Preferences > General > Apperance >  Colors and Fonts > Java > Java Editor Text Font > Edit

3. Templates: Window > Preferences > Java > Editor > Templates
	=> Befehl für das Pattern suchen oder anlegen
	so = System.out.println("");
	sp = System.out.print("");
	sof = System.out.print("", );

4. Auto Completion: Window > Preferences > Java > Editor >  Content Assist
	a) Enable Auto acitvation
	b) Set delay to 100ms
	c) paste in field "Auto activation triggers for Java:": ._abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ




Ablauf Einrichtung des Arbeitsplatzes:

1. Aufbau des Projektes in Eclipse
	Projekt = JavaCourse (allumfassend für den gesamten Kurs)
	Package = Section01 etc. (ein Package für jede Section)
	Class = Name des Programmierbeispiels und pro Package eine MainSection, welche das Kapitel startet

2. Erstellen des Projektes
	Windows-Zugriff: 	"C:\Users\noahk\Projekte\JavaCourse"
	WSL-Zugriff:		"/mnt/c/Users/noahk/Projekte/JavaCourse"

3. Git in WSL installieren
	sudo apt update
	sudo apt upgrade
	sudo apt install git
	git --Version

	git config --global user.name Noah
	git config --global user.email noahklein432+github@gmail.com

	cd /mnt/c/dev/java-kurs
	cat > .gitignore << 'EOF'
	/bin/
	/.settings/
	/.metadata/
	*.class
	*.log
	.DS_Store
	EOF
	
	git init
	git add .
	git commit -m "Initial Commit: Eclipse Java Project"

	ssh-keygen -t ed25519 -C "noahklein432+github@gmail.com"
	eval "$(ssh-agent -s)"
	ssh-add ~/.ssh/id_ed25519
	cat ~/.ssh/id_ed25519.pub

	git remote -v
	git remote set-url origin git@github.com:kle224/JavaCourse.git
	ssh -T git@github.com						// Testet Verbindung

	git remote add origin git@github.com:kle224/JavaCourse.git
	git push -u origin master


4. Git Stuff
	Create and switch branch: git switch -c <branch-name>

	Fetch all remote branches: git fetch --all --prune
	    => downloads information about all remote branches without changing local files

	Checkout existing remote branch: git switch -c <branch-name> --track origin/<branch-name>
	    => creates a local branch linked to the remote branch and switches to it

	Switch branch: git switch <branch-name>

	Rename local branch: git branch -m <old-name> <new-name>

	Delete branch (local): git branch -d <branch-name>
	    => deletes a local branch if it is already merged and safe to remove

	Delete branch (remote): git push origin --delete <branch-name>
	    => deletes the branch from the remote repository (GitHub)

	Fetch: git fetch
	    => checks the remote repository for new commits without modifying local code

	Pull: git pull
	    => fetches new commits from the remote repository and merges them into the current branch

	Push: git push
	    => uploads local commits to the remote repository

	Merge: git merge <branch-name>
	    => merges the specified branch into the currently checked-out branch

	Check current state: git status
	    => shows the current branch and the state of modified, staged, or untracked files

	Check current branch: git branch
	    => lists all local branches and marks the current one

	Check branch tracking: git branch -vv
	    => shows local branches and which remote branches they track

	View commit history: git log --oneline
	    => displays a compact list of recent commits

	View commit history of a branch: git log --oneline <branch-name>
	    => shows the commit history of a specific branch

	View new remote commits (after fetch): git log work..origin/work
	    => shows commits that exist remotely but not locally

	View differences (local changes): git diff
	    => shows uncommitted changes in the working directory

	View differences between local and remote branch: git diff work..origin/work
	    => shows differences between the local branch and the remote version

	View differences between two branches: git diff master..work
	    => shows what work contains that master does not

NICE TO KNOW:
notebook wsl: kle | G!1 | SSH-Phrase benteler
tower wsl:    noahk | G!1 | SSH-Phrase G!1
