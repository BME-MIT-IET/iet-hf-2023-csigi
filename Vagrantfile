VAGRANTFILE_API_VERSION = "2"
 
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.provision "docker" do |d|
    d.run "vandreas73/vilagtalan_virologusok",
      args: "-d -p 0.0.0.0:49153:8080"
  end
  config.vm.network :forwarded_port, host: 80, guest: 80
end