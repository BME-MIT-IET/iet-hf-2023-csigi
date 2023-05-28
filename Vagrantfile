VAGRANTFILE_API_VERSION = "2"
 
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "hashicorp/bionic64"
  config.vm.box_version = "1.0.282"
  config.vm.provision "docker" do |d|
    d.run "vandreas73/vilagtalan_virologusok:buildsuccessful"
  end
  config.vm.network :forwarded_port, host: 4567, guest: 49153
end